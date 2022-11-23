package com.example.pixabay

data class PixaModel(
    var hits:List<ImageModel>
) {
}
data class ImageModel (
    var largeImageURL:String
    )
