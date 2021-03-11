package idnull.znz.currency.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import idnull.znz.currency.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val mBinding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.usdInfo.observe(viewLifecycleOwner,{
            mBinding.aud.text= it.AUD.toString()
            mBinding.cny.text = it.CNY.toString()
            mBinding.usd.text = it.USD.toString()
            Log.d("TEST_USD", "3 отоброжение данных в fragment: $it")
        })
       return mBinding.root

    }


}