package com.eliasjuniornino.zapzap.ui.home.tabs

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.eliasjuniornino.zapzap.R
import com.eliasjuniornino.zapzap.databinding.FragmentCameraBinding
import com.google.android.material.tabs.TabLayout
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * A simple [Fragment] subclass.
 * Use the [CameraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CameraFragment : BaseTabListFragment() {
    override fun tabLayoutBind(tab: TabLayout.Tab) {
        tab.setIcon(R.drawable.ic_baseline_photo_camera_20)
    }

    private lateinit var binding: FragmentCameraBinding
    private var cameraExecutor: ExecutorService? = null
    private var initialized: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        initializeCamera()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeCamera()
    }

    override fun onResume() {
        super.onResume()
        initializeCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor?.shutdown()
        initialized = false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startCamera()
                }
            } else {
                Toast.makeText(context, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initializeCamera() {
        if (initialized) return

        if (allPermissionsGranted()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startCamera()
            }
        } else activity?.let {
            ActivityCompat.requestPermissions(it, REQUIRED_PERMISSIONS.toTypedArray(), 0)
        }
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {  permission ->
        context?.let {
            ContextCompat.checkSelfPermission(it, permission)
        } == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startCamera() {
        val cameraProviderFuture = context?.let { ProcessCameraProvider.getInstance(it) }

        cameraProviderFuture?.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also { it.setSurfaceProvider(binding.cameraPreview.surfaceProvider) }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
                initialized = true
            } catch (exec: Exception) {
                Toast.makeText(context, "Use case binding failed.", Toast.LENGTH_SHORT).show()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    companion object {
        private val REQUIRED_PERMISSIONS = when {
            Build.VERSION.SDK_INT <= Build.VERSION_CODES.P -> listOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            else -> listOf(
                Manifest.permission.CAMERA
            )
        }
        private const val REQUEST_CODE_PERMISSIONS = 32

        /**
         * @return A new instance of fragment ChatListFragment.
         */
        @JvmStatic
        fun newInstance() = CameraFragment()
    }
}