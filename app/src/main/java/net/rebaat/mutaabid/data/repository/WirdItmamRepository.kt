package net.rebaat.mutaabid.data.repository

import net.rebaat.mutaabid.data.model.WirdItmam
import java.util.Date

interface WirdItmamRepository {
    fun getWirdItmams(date: Date?): List<WirdItmam>
    suspend fun submitWirdItmams(wirdItmams: List<WirdItmam>): Boolean
}