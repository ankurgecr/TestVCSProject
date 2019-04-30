package info.ankurpandya.testvcsproject;

import android.graphics.Bitmap;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by ParthSoni on 4/23/2019.
 */
public class SapnaImageGridAdapter extends RecyclerView.Adapter<SapnaImageGridAdapter.GridItemViewHolder> {

    private List<Bitmap> imageList;

    public class GridItemViewHolder extends RecyclerView.ViewHolder {
        ImageView siv;

        public GridItemViewHolder(View view) {
            super(view);
            siv = view.findViewById(R.id.siv);
        }
    }

    public SapnaImageGridAdapter(List<Bitmap> imageList) {
        this.imageList = imageList;
    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sapna_grid, parent, false);
        return new GridItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        Bitmap bitmap = imageList.get(position);
        holder.siv.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

}
