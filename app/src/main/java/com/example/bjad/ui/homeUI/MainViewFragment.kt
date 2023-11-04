package com.example.bjad.ui.homeUI

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.recreate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bjad.Model.Results
import com.example.bjad.R
import com.example.bjad.databinding.FragmentMainViewBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main_view.*
import java.text.SimpleDateFormat
import java.util.*


private const val TAG = "MainViewFragment"

@AndroidEntryPoint
class MainViewFragment : Fragment(),NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: FragmentMainViewBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var sunSetSunriseRes:Results
    private lateinit var preferences:SharedPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)
        preferences = requireActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        setLocale(getLanguagePreference(),true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainViewBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreateView: ")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        navigationDrawer()

        val callback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (binding.homeDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    binding.homeDrawerLayout.closeDrawer(GravityCompat.START)
                    binding.navView.setCheckedItem(R.id.nav_home)
                }else{
                    activity?.moveTaskToBack(true)
                }

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)

        Log.d(TAG, "onViewCreated: ")
        val cal = Calendar.getInstance()
        val monthDate:SimpleDateFormat = if (Locale.getDefault().language.equals("en")){
            Log.d(TAG, "onViewCreated: en  ${Locale.getDefault()}")
            SimpleDateFormat("EEEE dd MMMM",Locale.ENGLISH)
        }else if(Locale.getDefault().language.equals("hi")){
            SimpleDateFormat("EEEE dd MMMM",Locale("hi","IN"))
        }else{
            SimpleDateFormat("EEEE dd MMMM",Locale.ENGLISH)
        }

        val monthName = monthDate.format(cal.time)
        binding.dateHome.text = monthName.split(" ")[1] +"th "+ monthName.split(" ")[2].toUpperCase()
        binding.weekDayTV.text = monthName.split(" ")[0]
        //setUserName()
        //setSunsetSunriseTime()

        binding.photos.setOnClickListener(){
            findNavController().navigate(R.id.action_mainViewFragment_to_photosFragment,Bundle().apply {  })
        }
        binding.audioSongs.setOnClickListener(){
            findNavController().navigate(R.id.action_mainViewFragment_to_audioActivity,Bundle().apply {  })
        }
        binding.videoSongs.setOnClickListener(){
            findNavController().navigate(R.id.action_mainViewFragment_to_videoList2,Bundle().apply {  })
        }

        binding.aartiIcon.setOnClickListener {
            findNavController().navigate(R.id.action_mainViewFragment_to_aarti,Bundle().apply {  })
        }


    }

    private fun navigationDrawer() {
        binding.navView.bringToFront()
        binding.navView.setNavigationItemSelectedListener(this)
        binding.navView.setCheckedItem(R.id.nav_home)

        binding.menuHome.setOnClickListener {
            if(binding.homeDrawerLayout.isDrawerVisible(GravityCompat.START)){
                binding.homeDrawerLayout.closeDrawer(GravityCompat.START)
            }else{
                binding.homeDrawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }


    // how to get the data using retrofit api
  /*  private fun setSunsetSunriseTime() {
        viewModel.getSunsetSunrisedata.observe(viewLifecycleOwner) {
            when (it)  {
                is UiState.Success -> {
                    binding.sunrise.setText(it.data.results.sunrise)
                    binding.sunset.setText(it.data.results.sunset)
                }
                else -> {}
            }

        }

    }*/


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onNavigationItemSelected: ")
        return when (item.itemId) {
            R.id.nav_home -> {
                binding.homeDrawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.nav_music -> {
                findNavController().navigate(
                    R.id.action_mainViewFragment_to_audioActivity,
                    Bundle().apply { })
                true
            }
            R.id.nav_video -> {
                findNavController().navigate(
                    R.id.action_mainViewFragment_to_videoList2,
                    Bundle().apply { })
                true
            }
            R.id.nav_about -> {
                Toast.makeText(requireContext(), "About!!!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_contact_info -> {
                Toast.makeText(requireContext(), "Info!!!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_rate_us -> {
                Toast.makeText(requireContext(), "Rate us!!!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_share -> {
                Toast.makeText(requireContext(), "share!!!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_hindi ->{
                if (Locale.getDefault().language == "en")
                    saveLanguagePreference("hi")
                    setLocale("hi",false)
                true
            }
            R.id.nav_english -> {
                if (Locale.getDefault().language == "hi"){
                    saveLanguagePreference("en")
                    setLocale("en",false)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun setLocale(langCode:String,comeFromcreateMethod:Boolean){
        Log.d(TAG, "setLocale: $langCode")
        val locale = Locale(langCode)
        Locale.setDefault(locale)

        val resources =requireContext().resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        if (!comeFromcreateMethod)  recreate(requireActivity())
    }

    fun getLanguagePreference(): String {
        //val preferences = activity?.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val language = preferences.getString("language", "en") ?: ""
        Log.d(TAG, "getLanguagePreference: language :- $preferences - -  $language")
        return language
    }

    private fun saveLanguagePreference(language: String) {
        Log.d(TAG, "saveLanguagePreference: $language")
        val editor = preferences.edit()
        editor?.putString("language", language)
        editor?.apply()
    }


}




