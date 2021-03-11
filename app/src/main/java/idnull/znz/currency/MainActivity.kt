package idnull.znz.currency
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import idnull.znz.currency.databinding.ActivityMainBinding
import idnull.znz.currency.utils.APP


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding:ActivityMainBinding? = null
            val mBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {

        APP = this
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val navView= mBinding.navView
        val navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}