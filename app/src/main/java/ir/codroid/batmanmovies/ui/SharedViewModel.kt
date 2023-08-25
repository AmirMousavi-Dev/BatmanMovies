package ir.codroid.batmanmovies.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.data.remote.ApiService
import ir.codroid.batmanmovies.data.remote.NetWorkResult
import ir.codroid.batmanmovies.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel
@Inject constructor(
    private val repository: MovieRepository,
    private val apiService: ApiService,
) : ViewModel() {

    val _movieList = MutableStateFlow<NetWorkResult<List<Movie>>>(NetWorkResult.Loading())
    val movieList = _movieList.asStateFlow()

    val _movieDetail = MutableStateFlow<NetWorkResult<MovieDetail>>(NetWorkResult.Loading())
    val movieDetail = _movieDetail.asStateFlow()

    private var job: Job? = null
    fun getMovieList() {
        viewModelScope.launch {
            val movieListLocal = repository.getMovieListLocal()
            if (movieListLocal.isEmpty()) {
                val movieListRemote = repository.getMovieListRemote()
                _movieList.value = movieListRemote
                if (movieListRemote is NetWorkResult.Success) {
                    movieListRemote.data?.let {
                        insertMovieList(it)
                    }
                }
            } else {
                _movieList.emit(NetWorkResult.Success(movieListLocal))
            }
        }
    }

    fun getMovieDetail(imdbId: String) {


        viewModelScope.launch {
            val isMovieExist =
                async(Dispatchers.IO) {
                    repository.isMovieExist(imdbId)
                }
            if (!isMovieExist.await()) {
                try {
                    val movieDetailRemote = async {
                        return@async repository.getMovieDetailRemote(imdbId)
                    }
                    if (movieDetailRemote.await().isSuccessful) {
                        movieDetailRemote.await().body()?.let {

                            _movieDetail.emit(NetWorkResult.Success(it))
                            insertMovieDetail(it)
                        }

                    }

                } catch (e: Exception) {


                }


            } else {
                val movieDetailLocal = async(Dispatchers.IO) {
                    repository.getMovieDetailLocal(imdbId)
                }
                _movieDetail.emit(NetWorkResult.Success(movieDetailLocal.await()))
//
            }
        }
    }

    private fun insertMovieList(movieList: List<Movie>) {
        job?.cancel()
        job = viewModelScope.launch {
            repository.insertMovieListLocal(movieList)
        }
    }

    fun insertMovieDetail(movieDetail: MovieDetail) {
        viewModelScope.launch {
            repository.insertMovieDetailLocal(movieDetail)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}