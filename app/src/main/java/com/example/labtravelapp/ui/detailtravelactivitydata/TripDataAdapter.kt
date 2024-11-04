package com.example.labtravelapp.ui.detailtravelactivitydata

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labtravelapp.R
import com.example.labtravelapp.Trip

class TripDataAdapter(
    private val context: Context, 
    var data: Trip,
    private val onNotesChanged: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = "TripDataAdapter"
    private val VIEW_TYPE_TEXT = 0
    private val VIEW_TYPE_NOTES = 1

    private val tripDetails: List<Pair<String, Int>>
        get() = listOf(
            Pair("<b>${context.getString(R.string.trip_name_text)}</b>: ${data.name}", VIEW_TYPE_TEXT),
            Pair("<b>${context.getString(R.string.trip_description_text)}</b>: ${data.description}", VIEW_TYPE_TEXT),
            Pair("<b>${context.getString(R.string.trip_notes_text)}</b>", VIEW_TYPE_NOTES),
            Pair("<b>${context.getString(R.string.trip_cost_text)}</b>: ${data.cost}", VIEW_TYPE_TEXT),
            Pair("<b>${context.getString(R.string.trip_rating_text)}</b>: ${data.rating}", VIEW_TYPE_TEXT)
        )

    override fun getItemViewType(position: Int): Int {
        return tripDetails[position].second
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NOTES -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_trip_notes, parent, false)
                NotesViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_trip_detail, parent, false)
                TextViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            when (holder) {
                is TextViewHolder -> {
                    holder.detailTextView.text = android.text.Html.fromHtml(tripDetails[position].first)
                }
                is NotesViewHolder -> {
                    Log.d(TAG, "Binding notes: ${data.notes}")
                    holder.notesEditText.setText(data.notes)
                    holder.notesEditText.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                        override fun afterTextChanged(s: Editable?) {
                            try {
                                val newNotes = s.toString()
                                Log.d(TAG, "Notes changed to: $newNotes")
                                onNotesChanged(newNotes)
                            } catch (e: Exception) {
                                Log.e(TAG, "Error in TextWatcher", e)
                            }
                        }
                    })
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in onBindViewHolder", e)
        }
    }

    override fun getItemCount(): Int = tripDetails.size

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val detailTextView: TextView = view.findViewById(R.id.trip_detail)
    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val notesEditText: EditText = view.findViewById(R.id.notes_edit_text)
    }
}
