package com.weather.datastore.usecases

/**
 * Custom exception thrown when city name is null or not found in DataStore.
 * 
 * @param message The error message to display. Defaults to "City name not found in DataStore"
 */
class CityNameNotFoundException(message: String = "City name not found in DataStore") : Exception(message)