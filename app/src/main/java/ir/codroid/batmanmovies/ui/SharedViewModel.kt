package ir.codroid.batmanmovies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.data.remote.ApiService
import ir.codroid.batmanmovies.data.remote.NetWorkResult
import ir.codroid.batmanmovies.data.repository.MovieRepository
import kotlinx.coroutines.Job
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

    private val _movieDetail = MutableStateFlow<NetWorkResult<MovieDetail>>(NetWorkResult.Loading())
    val movieDetail = _movieDetail.asStateFlow()

    private var job: Job? = null
    fun getMovieList() {
        viewModelScope.launch {
            _movieList.emit(repository.getMovieListRemote())

//            val movieListLocal = repository.getMovieListLocal()
//            if (movieListLocal.isEmpty()) {
//                _movieList.value = repository.getMovieListRemote()
//                job?.cancel()
//            } else {
//                _movieList.emit(Movies("true", movieListLocal, ""))
//            }
        }
    }

    fun getMovieDetail(imdbId: Int) {
//        job?.cancel()
//        job = viewModelScope.launch {
//            val movieDetailLocal = repository.getMovieDetailLocal(imdbId)
//            if (movieDetailLocal == null) {
//                _movieDetail.emit(repository.getMovieDetailRemote(imdbId))
//
//            } else {
//                _movieDetail.emit(NetWorkResult.Success(movieDetailLocal, ""))
//            }
//        }
    }

    fun insertMovieList(movieList: List<Movie>) {
        job?.cancel()
        job = viewModelScope.launch {
            repository.insertMovieListLocal(movieList)
        }
    }

    fun insertMovieDetail(movieDetail: MovieDetail) {
        job?.cancel()
        job = viewModelScope.launch {
            repository.insertMovieDetailLocal(movieDetail)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}