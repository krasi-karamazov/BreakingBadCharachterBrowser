package kpk.dev.presentation

sealed class ResourceState {
    object LOADING: ResourceState()
    object SUCCESS: ResourceState()
    object FAILURE: ResourceState()
}