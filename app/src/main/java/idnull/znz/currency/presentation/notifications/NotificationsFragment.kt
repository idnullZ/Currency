package idnull.znz.currency.presentation.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import idnull.znz.currency.databinding.FragmentNotificationsBinding
import idnull.znz.currency.presentation.adapters.RatesAdapter

@AndroidEntryPoint
class NotificationsFragment : Fragment() {
    private var _binding:FragmentNotificationsBinding? =null
    val mBinding get() = _binding!!
    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(layoutInflater, container, false)


        val adapter = RatesAdapter()


        mBinding.rvRates.adapter = adapter

        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)

        notificationsViewModel.ratesInfo.observe(viewLifecycleOwner,{

         adapter.setData(it)

        })

        return mBinding.root
    }
}