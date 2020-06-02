package com.djevtic.myswifttestcode.extensions

import android.os.Looper
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

val WORKER_SCHEDULER = Schedulers.io()
val MAIN_SCHEDULER = AndroidSchedulers.mainThread()!!

//Observable

fun <T> Observable<T>.ioToMain(): Observable<T> =
    compose { subscribeOn(WORKER_SCHEDULER).observeOn(MAIN_SCHEDULER) }

fun <T> Observable<T>.computationToMain(): Observable<T> =
    compose { subscribeOn(Schedulers.computation()).observeOn(MAIN_SCHEDULER) }

fun <T> Observable<T>.toWorker(): Observable<T> = compose { subscribeAsync().observeAsync() }
fun <T> Observable<T>.subscribeAsync(): Observable<T> = subscribeOn(WORKER_SCHEDULER)
fun <T> Observable<T>.subscribeMain(): Observable<T> = subscribeOn(MAIN_SCHEDULER)
fun <T> Observable<T>.observeAsync(): Observable<T> = observeOn(WORKER_SCHEDULER)
fun <T> Observable<T>.observeMain(): Observable<T> = observeOn(MAIN_SCHEDULER)
fun <T> Observable<T>.onErrorResumeNextKt(f: ((t: Throwable) -> ObservableSource<T>)): Observable<T> =
    onErrorResumeNext(Function { f.invoke(it) })

fun Observable<*>.asUnit(): Observable<Unit> = map { Unit }

inline fun <reified T> Observable<in T>.filterToClass(predicate: Predicate<T>? = null): Observable<T> {
    val casted = this.filter { it is T }.cast(T::class.java)
    return predicate?.let { casted.filter(it) } ?: casted
}

//Maybe

fun <T> Maybe<T>.ioToMain(): Maybe<T> =
    compose { subscribeOn(WORKER_SCHEDULER).observeOn(MAIN_SCHEDULER) }

fun <T> Maybe<T>.workerToMain(): Maybe<T> = compose { subscribeAsync().observeMain() }
fun <T> Maybe<T>.computationToMain(): Maybe<T> =
    compose { subscribeOn(Schedulers.computation()).observeOn(MAIN_SCHEDULER) }

fun <T> Maybe<T>.toWorker(): Maybe<T> = compose { subscribeAsync().observeAsync() }
fun <T> Maybe<T>.subscribeAsync(): Maybe<T> = subscribeOn(WORKER_SCHEDULER)
fun <T> Maybe<T>.subscribeMain(): Maybe<T> = subscribeOn(MAIN_SCHEDULER)
fun <T> Maybe<T>.observeAsync(): Maybe<T> = observeOn(WORKER_SCHEDULER)
fun <T> Maybe<T>.observeMain(): Maybe<T> = observeOn(MAIN_SCHEDULER)
fun <T> Maybe<T>.onErrorResumeNextKt(f: ((t: Throwable) -> MaybeSource<T>)): Maybe<T> =
    onErrorResumeNext(Function { f.invoke(it) })

//Single

fun <T> Single<T>.ioToMain(): Single<T> =
    compose { subscribeOn(WORKER_SCHEDULER).observeOn(MAIN_SCHEDULER) }

fun <T> Single<T>.workerToMain(): Single<T> = compose { subscribeAsync().observeMain() }
fun <T> Single<T>.computationToMain(): Single<T> =
    compose { subscribeOn(Schedulers.computation()).observeOn(MAIN_SCHEDULER) }

fun <T> Single<T>.toWorker(): Single<T> = compose { subscribeAsync().observeAsync() }
fun <T> Single<T>.subscribeAsync(): Single<T> = subscribeOn(WORKER_SCHEDULER)
fun <T> Single<T>.subscribeMain(): Single<T> = subscribeOn(MAIN_SCHEDULER)
fun <T> Single<T>.observeAsync(): Single<T> = observeOn(WORKER_SCHEDULER)
fun <T> Single<T>.observeMain(): Single<T> = observeOn(MAIN_SCHEDULER)
fun <T> Single<T>.subscribeTo(func: (T) -> Unit) = subscribe({ func(it) }, {})

//Flowable

fun <T> Flowable<T>.ioToMain(): Flowable<T> =
    compose { subscribeOn(WORKER_SCHEDULER).observeOn(MAIN_SCHEDULER) }

fun <T> Flowable<T>.workerToMain(): Flowable<T> = compose { subscribeAsync().observeMain() }
fun <T> Flowable<T>.computationToMain(): Flowable<T> =
    compose { subscribeOn(Schedulers.computation()).observeOn(MAIN_SCHEDULER) }

fun <T> Flowable<T>.toWorker(): Flowable<T> = compose { subscribeAsync().observeAsync() }
fun <T> Flowable<T>.subscribeAsync(): Flowable<T> = subscribeOn(WORKER_SCHEDULER)
fun <T> Flowable<T>.subscribeMain(): Flowable<T> = subscribeOn(MAIN_SCHEDULER)
fun <T> Flowable<T>.observeAsync(): Flowable<T> = observeOn(WORKER_SCHEDULER)
fun <T> Flowable<T>.observeMain(): Flowable<T> = observeOn(MAIN_SCHEDULER)
fun <T> Flowable<T>.onErrorResumeNextKt(f: ((t: Throwable) -> Publisher<T>)): Flowable<T> =
    onErrorResumeNext(Function { f.invoke(it) })

//Completable

fun Completable.subscribeAsync(): Completable = subscribeOn(WORKER_SCHEDULER)
fun Completable.ioToMain(): Completable =
    compose { subscribeOn(WORKER_SCHEDULER).observeOn(MAIN_SCHEDULER) }

fun Completable.observeAsync(): Completable = observeOn(WORKER_SCHEDULER)
fun Completable.computationToMain(): Completable =
    compose { subscribeOn(Schedulers.computation()).observeOn(MAIN_SCHEDULER) }

fun Completable.observeMain(): Completable = observeOn(MAIN_SCHEDULER)
fun Completable.subscribeTo(func: () -> Unit) = subscribe({ func() }, {})

fun isMainThread() = Looper.getMainLooper().thread == Thread.currentThread()

val emptySignal = RxSignal.Empty()

sealed class RxSignal {
    class Empty : RxSignal()
}