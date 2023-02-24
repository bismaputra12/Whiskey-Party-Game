package emeraldbismaputra.practice.myapplication

import android.app.Application

class CardApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // this file initializes the CardRepository class/file
        CardRepository.initialize(this)
    }
}