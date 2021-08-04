package com.example.tradfriclient.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.tradfriclient.R
import com.example.tradfriclient.data.util.SessionManager
import com.example.tradfriclient.databinding.ActivityMainBinding
import com.example.tradfriclient.presentation.viewmodel.DetailsViewModel
import com.example.tradfriclient.presentation.viewmodel.LoginViewModel
import com.example.tradfriclient.presentation.viewmodel.RoomsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val loginViewModel: LoginViewModel by viewModels()
    val roomsViewModel: RoomsViewModel by viewModels()
    val detailsViewModel: DetailsViewModel by viewModels()
    private lateinit var sessionManager: SessionManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        sessionManager = SessionManager(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        if (navController.currentDestination == null) {
            val graph = navController.navInflater.inflate(R.navigation.nav_graph)
            graph.startDestination = getStartDestination()
            navController.graph = graph
        }
        binding.bnvRooms.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, navDestination: NavDestination, _ ->
            binding.bnvRooms.isVisible = navDestination.id == R.id.roomsFragment || navDestination.id == R.id.settingsFragment
        }
        setContentView(binding.root)
    }

    private fun getStartDestination(): Int {
        return if (isLoggedIn()) {
            return R.id.roomsFragment
        } else {
            R.id.loginFragment
        }
    }

    private fun isLoggedIn(): Boolean =
        !sessionManager.fetchAuthToken().isNullOrBlank()
}