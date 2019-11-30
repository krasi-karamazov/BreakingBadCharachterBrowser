package kpk.dev.caching

import android.content.Context
import com.pacoworks.rxpaper2.RxPaperBook
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PaperCache<T> @Inject constructor() {

    object RxPaperInitializer {
        fun init(context: Context) = RxPaperBook.init(context)
    }

    private val book: RxPaperBook = RxPaperBook.with(Schedulers.io())

    fun put(key: String, obj: T): Single<T> = book.write(key, obj).toSingleDefault(obj)

    fun get(key: String): Single<T> = book.read(key)

}