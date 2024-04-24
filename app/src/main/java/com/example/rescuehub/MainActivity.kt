package com.example.rescuehub

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        setWindowInsets()

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Request location permission
        requestLocationPermission()

        // Example usage: Register a new user
        registerUser("example@gmail.com", "password")

        // Example usage: Sign in an existing user
        signInUser("example@gmail.com", "password")
    }

    // Enable edge-to-edge display
    private fun enableEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Set window insets


    private fun setWindowInsets() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }



    // Request location permission
    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // If permission is already granted, get the user's location
            getUserLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, get the user's location
                getUserLocation()
            } else {
                // Permission denied, handle accordingly
                // For example, show a message or disable location-related functionality
            }
        }
    }

    // Code to get current user location
    private fun getUserLocation() {
        // Use LocationManager or FusedLocationProviderClient to get the user's location
        // Store the user's location in Firebase Realtime Database or Firestore
    }

    // Code to send emergency alert
    private fun sendEmergencyAlert() {
        // Use Firebase Cloud Messaging to send push notifications
        // Handle success or failure of sending alert
    }

    // Code to send and receive messages in real-time
    private fun sendMessage(message: String) {
        // Send message to Firebase Realtime Database or Firestore
        // Handle success or failure of sending message
    }

    private fun receiveMessage() {
        // Listen for new messages from Firebase Realtime Database or Firestore
        // Handle incoming messages
    }

    // Code to optimize routes for volunteers
    private fun optimizeRoutes() {
        // Use ML algorithms to analyze real-time traffic data and optimize routes
        // Handle success or failure of route optimization
    }

    // Code to monitor user movements and detect distress
    private fun monitorUserMovements() {
        // Use ML algorithms to monitor user movements and detect distress
        // Handle detected distress
    }

    // Code to analyze environmental sounds and user activity
    private fun analyzeEnvironmentalSounds() {
        // Use ML models to analyze environmental sounds and user activity
        // Handle detected safety concerns
    }

    // Example function to register a new user with email and password
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@MainActivity) { task ->
                if (task.isSuccessful) {
                    // User registration successful
                    val user = auth.currentUser
                    // Proceed with your app logic (e.g., navigate to the next screen)
                } else {
                    // User registration failed
                    Toast.makeText(this@MainActivity, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    // Example function to sign in an existing user with email and password
    private fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@MainActivity) { task ->
                if (task.isSuccessful) {
                    // Sign-in successful
                    val user = auth.currentUser
                    // Proceed with your app logic (e.g., navigate to the next screen)
                } else {
                    // Sign-in failed
                    Toast.makeText(this@MainActivity, "Sign-in failed: ${task.exception?.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}
