package pl.marchuck.workmanagerexample.database

import io.reactivex.Observable

class s {

    internal fun ss() {


        Observable.fromArray(1, 2, 3, 23, 2, 34234, 23, 43, 42, 432, 4, 23)
                .subscribe({ integer ->
                    println("lol " + integer!!)
                    println("lol $integer")
                }, { integer ->
                    println("lol $integer")
                    println("lol $integer")
                })

    }
}
