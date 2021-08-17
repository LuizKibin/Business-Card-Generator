package me.lc_vi.businesscard

import android.app.Application
import me.lc_vi.businesscard.data.AppDatabase
import me.lc_vi.businesscard.data.BusinessCardRepository

class App : Application() {

    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy {BusinessCardRepository(database.businessDao())}

}