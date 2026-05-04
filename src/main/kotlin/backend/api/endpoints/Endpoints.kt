package org.example.backend.api.endpoints

import org.example.backend.api.RetrofitClient

open class Endpoints {
    protected val auth: AuthEndpoints by lazy { RetrofitClient.createService(AuthEndpoints::class.java) }
    protected val users: UserEndpoints by lazy { RetrofitClient.createService(UserEndpoints::class.java) }
    protected val products: ProductsEndpoints by lazy { RetrofitClient.createService(ProductsEndpoints::class.java) }
}