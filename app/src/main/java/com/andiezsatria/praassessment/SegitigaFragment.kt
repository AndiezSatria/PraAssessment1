package com.andiezsatria.praassessment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.andiezsatria.praassessment.databinding.FragmentSegitigaBinding
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * A simple [Fragment] subclass.
 */
class SegitigaFragment : Fragment() {

    companion object{
        private const val ALAS_KEY = "key_alas"
        private const val TINGGI_KEY = "key_tinggi"
        private const val LUAS_KEY = "key_luas"
        private const val KELILING_KEY = "key_keliling"
    }

    private var alas: Int = 0
    private var tinggi: Int = 0
    private var luas: Double = 0.0
    private var keliling: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSegitigaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_segitiga, container, false)
        binding.apply {
            if (savedInstanceState != null) {
                alas = savedInstanceState.getInt(ALAS_KEY)
                tinggi = savedInstanceState.getInt(TINGGI_KEY)
                luas = savedInstanceState.getDouble(LUAS_KEY)
                keliling = savedInstanceState.getDouble(KELILING_KEY)
                edtAlas.setText(alas.toString())
                edtTinggi.setText(tinggi.toString())
                tvLuas.text = "Luas = $luas"
                tvKeliling.text = "Keliling = $keliling"
            }
            btnHitung.setOnClickListener{
                alas = edtAlas.text.toString().toInt()
                tinggi = edtTinggi.text.toString().toInt()
                luas = alas * tinggi * 0.5
                keliling = alas + tinggi + (sqrt((alas.toDouble().pow(2) + tinggi.toDouble().pow(2))))
                tvLuas.text = "Luas = $luas"
                tvKeliling.text = "Keliling = $keliling"
            }

            btnShare.setOnClickListener{
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, "${tvLuas.text}, ${tvKeliling.text}")
                startActivity(shareIntent)
            }
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ALAS_KEY, alas)
        outState.putInt(TINGGI_KEY, tinggi)
        outState.putDouble(LUAS_KEY, luas)
        outState.putDouble(KELILING_KEY, keliling)
    }
}
