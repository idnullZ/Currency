package idnull.znz.currency.presentation.coin
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import idnull.znz.currency.databinding.FragmentCoinBinding
import idnull.znz.currency.domain.pojocoin.CoinPriceInfo
import idnull.znz.currency.presentation.adapters.CoinInfoAdapter


@AndroidEntryPoint
class CoinFragment : Fragment() {
    private var _binding: FragmentCoinBinding? = null
    val mBinding get() = _binding!!
    private lateinit var viewModel: CoinViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinBinding.inflate(layoutInflater, container, false)
        val adapter = CoinInfoAdapter(this)


        mBinding.rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        viewModel.priceList.observe(viewLifecycleOwner, Observer {
            adapter.coinInfoList = it
        })
        return mBinding.root
    }
}