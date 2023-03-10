package br.com.permissions

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AccompanistPermissionsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
        CameraPermission(permissionState = permissionState)
        Button(
            onClick = {
                permissionState.launchPermissionRequest()
            }
        ) {
            Text(text = "Open Camera")
        }

        val multiplePermissionState = rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        LocationPermissions(multiplePermissionState = multiplePermissionState)
        Button(
            onClick = {
                multiplePermissionState.launchMultiplePermissionRequest()
            }
        ) {
            Text(text = "Use location")
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermission(
    permissionState: PermissionState,
) {
    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = { /* ... */ },
        permissionNotAvailableContent = { /* ... */ }
    ) {
        // Open Camera
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissions(
    multiplePermissionState: MultiplePermissionsState
) {
    PermissionsRequired(
        multiplePermissionsState = multiplePermissionState,
        permissionsNotGrantedContent = { /* ... */ },
        permissionsNotAvailableContent = { /* ... */ }
    ) {
        // Use location
    }
}