package io.brick.springmvc.web.frontcontroller

class ModelView(
    var viewName: String
) {
    var model: MutableMap<String, Any> = mutableMapOf()
}