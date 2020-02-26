package com.andiezsatria.praassessment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.andiezsatria.praassessment.databinding.FragmentPersegiPanjangBinding

/**
 * A simple [Fragment] subclass.
 */
class PersegiPanjangFragment : Fragment() {

    companion object {
        private const val PANJANG_KEY = "key_panjang"
        private const val LEBAR_KEY = "key_lebar"
        private const val LUAS_KEY = "key_luas"
        private const val KELILING_KEY = "key_keliling"
    }

    private var panjang: Int = 0
    private var lebar: Int = 0
    private var luas: Int = 0
    private var keliling: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPersegiPanjangBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_persegi_panjang, container, false)
        binding.apply {
            if (savedInstanceState != null) {
                panjang = savedInstanceState.getInt(PANJANG_KEY)
                lebar = savedInstanceState.getInt(LEBAR_KEY)
                luas = savedInstanceState.getInt(LUAS_KEY)
                keliling = savedInstanceState.getInt(KELILING_KEY)
                edtPanjang.setText(panjang.toString())
                edtLebar.setText(lebar.toString())
                tvLuas.text = "Luas = $luas"
                tvKeliling.text = "Keliling = $keliling"
            }
            btnHitung.setOnClickListener {
                panjang = edtPanjang.text.toString().toInt()
                lebar = edtLebar.text.toString().toInt()
                luas = panjang * lebar
                keliling = 2 * (panjang + lebar)
                tvLuas.text = "Luas = $luas"
                tvKeliling.text = "Keliling = $keliling"
            }
            btnShare.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain")
                    .putExtra(Intent.EXTRA_TEXT, "${tvLuas.text}, ${tvKeliling.text}")
                startActivity(shareIntent)
            }
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PANJANG_KEY, panjang)
        outState.putInt(LEBAR_KEY, lebar)
        outState.putInt(LUAS_KEY, luas)
        outState.putInt(KELILING_KEY, keliling)
    }
}
