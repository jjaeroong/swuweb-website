package com.swuweb.swuweb.application.dto.response

data class AdminApplicationLinksResponse(
    val applications: List<AdminApplicationLinkItemResponse>,
)
data class AdminApplicationLinkItemResponse(
    val applicationShareId: Long?,
    val link: String,
    val name: String?,
)