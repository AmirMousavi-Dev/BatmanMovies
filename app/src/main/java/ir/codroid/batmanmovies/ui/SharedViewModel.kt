package ir.codroid.batmanmovies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.data.remote.NetWorkResult
import ir.codroid.batmanmovies.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel
@Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _movieList = MutableStateFlow<NetWorkResult<List<Movie>>>(NetWorkResult.Loading())
    val movieList = _movieList.asStateFlow()

    private val _movieDetail = MutableStateFlow<NetWorkResult<MovieDetail>>(NetWorkResult.Loading())
    val movieDetail = _movieDetail.asStateFlow()

    private val _similarMovie = MutableStateFlow<List<Movie>>(emptyList())
    val similarMovie = _similarMovie.asStateFlow()

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
                    val response = repository.getMovieDetailRemote(imdbId)

                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.let {

                            _movieDetail.emit(NetWorkResult.Success(it))
                            insertMovieDetail(it)
                        }

                    } else
                    _movieDetail.emit(NetWorkResult.Error(false, "error"))


                } catch (e: Exception) {
                    _movieDetail.emit(NetWorkResult.Error(false, e.message.toString()))

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

    fun getSimilarMovie() {
        viewModelScope.launch {
            val movies = async { repository.getMovieListLocal() }
            _similarMovie.emit(movies.await())
        }
    }

    private fun insertMovieList(movieList: List<Movie>) {
        viewModelScope.launch {
            repository.insertMovieListLocal(movieList)
        }
    }

    private fun insertMovieDetail(movieDetail: MovieDetail) {
        viewModelScope.launch {
            repository.insertMovieDetailLocal(movieDetail)
        }
    }

}