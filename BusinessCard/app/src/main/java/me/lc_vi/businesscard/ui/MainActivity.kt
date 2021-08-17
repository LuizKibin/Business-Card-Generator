package me.lc_vi.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import me.lc_vi.businesscard.App
import me.lc_vi.businesscard.databinding.ActivityMainBinding
import me.lc_vi.businesscard.util.Image
import me.lc_vi.businesscard.R


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {

        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBusinessCard()
        insertListener()
    }

    private fun insertListener() {

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }


        binding.btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            builder.setTitle(getString(R.string.confirm))
            builder.setMessage(getString(R.string.confirm_message))

            builder.setPositiveButton(getString(R.string.confirm_sim)) { dialog, which -> // Do nothing but close the dialog
                mainViewModel.deleteAll()
                dialog.dismiss()
            }

            builder.setNegativeButton(
                    getString(R.string.confirm_nao)
            ) { dialog, which -> // Do nothing
                dialog.dismiss()
            }

            val alert = builder.create()
            alert.show()
        }


        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }

    }


    private fun getAllBusinessCard() {

        mainViewModel.getAll().observe(this, { businessCards ->
            adapter.submitList(businessCards)
        })

    }

}