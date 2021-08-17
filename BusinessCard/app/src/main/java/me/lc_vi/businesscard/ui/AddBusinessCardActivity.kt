package me.lc_vi.businesscard.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import me.lc_vi.businesscard.App
import me.lc_vi.businesscard.data.BusinessCard
import me.lc_vi.businesscard.databinding.ActivityAddBusinessCardBinding


class AddBusinessCardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {

        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListener()
    }

    private fun insertListener() {
        binding.btnClose.setOnClickListener {

            finish()
        }

        binding.btnConfirm.setOnClickListener {

            if (binding.tilNome.editText?.text.toString().isEmpty()
                    or binding.tilEmpresa.editText?.text.toString().isEmpty()
                    or binding.tilTelefone.editText?.text.toString().isEmpty()
                    or binding.tilMail.editText?.text.toString().isEmpty()
                    or binding.tilCargo.editText?.text.toString().isEmpty()
                    or binding.tilCor.editText?.text.toString().isEmpty()
            ) {

                Toast.makeText(this, me.lc_vi.businesscard.R.string.label_show_fail, Toast.LENGTH_SHORT).show()
            } else {


                if ((binding.tilNome.editText?.text.toString().length > 20)
                        or (binding.tilCargo.editText?.text.toString().length > 20)) {

                    Toast.makeText(this, me.lc_vi.businesscard.R.string.label_show_fail2, Toast.LENGTH_SHORT).show()
                } else {

                    val businessCard = BusinessCard(
                            nome = binding.tilNome.editText?.text.toString(),
                            empresa = binding.tilEmpresa.editText?.text.toString(),
                            telefone = binding.tilTelefone.editText?.text.toString(),
                            email = binding.tilMail.editText?.text.toString(),
                            cargo = binding.tilCargo.editText?.text.toString(),
                            fundoPersonalizado = binding.tilCor.editText?.text.toString()
                    )

                    mainViewModel.insert(businessCard)
                    Toast.makeText(this, me.lc_vi.businesscard.R.string.label_show_sucess, Toast.LENGTH_SHORT).show()
                    finish()

                }
            }
        }
    }
}



