package kpk.dev.presentation

data class Resource<out T> (val resourceState: ResourceState,
                            val data: T? = null,
                            val message: String? = null
)