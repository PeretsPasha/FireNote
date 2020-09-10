package com.example.firenote;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NotesRecyclerAdapter extends FirestoreRecyclerAdapter<Note, NotesRecyclerAdapter.NoteViewHolder> {

    public NotesRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        holder.noteTextView.setText(note.getText());
        holder.checkBox.setChecked(note.isCompleted());
        CharSequence dateCharSeq = DateFormat.format("EEEE MMM d, yyyy h:mm:ss a", note.getCreated().toDate());
        holder.dateTextView.setText(dateCharSeq);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.note_row, parent, false);
        return new NoteViewHolder(view);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView noteTextView, dateTextView;
        CheckBox checkBox;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            noteTextView = itemView.findViewById(R.id.noteTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
