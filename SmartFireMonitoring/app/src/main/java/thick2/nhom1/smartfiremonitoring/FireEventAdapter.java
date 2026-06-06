package thick2.nhom1.smartfiremonitoring;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FireEventAdapter extends RecyclerView.Adapter<FireEventAdapter.FireEventViewHolder> {

    private final List<FireEvent> items = new ArrayList<>();

    public void submitList(List<FireEvent> newItems) {
        // Nhận danh sách mới từ fragment và làm mới RecyclerView
        items.clear();
        if (newItems != null) {
            items.addAll(newItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FireEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fire_event, parent, false);
        return new FireEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FireEventViewHolder holder, int position) {
        // Đổ dữ liệu của từng sự kiện cháy vào card hiển thị
        FireEvent event = items.get(position);

        holder.tvTime.setText("🔥 " + event.getDisplayTime());
        holder.tvTemperature.setText(String.format(Locale.getDefault(),
                "Nhiệt: %.1f°C | MQ2: %d", event.getTemperature(), event.getMq2Value()));
        holder.tvDirection.setText("Hướng: " + event.getFlameDirection());
        holder.tvPattern.setText("Pattern: " + event.getFlamePattern());
        holder.tvAction.setText("Xử lý: " + event.getActionTaken());
        holder.tvDuration.setText("Thời gian: " + event.getProcessingDuration());
        String triggerSource = event.getTriggerSourceLabel();
        holder.tvLevel.setText(triggerSource);

        int badgeColor;
        int badgeTextColor;
        if (event.isSensorFusionTriggered()) {
            badgeColor = Color.parseColor("#FFEDD5");
            badgeTextColor = Color.parseColor("#9A3412");
            holder.cardRoot.setStrokeColor(Color.parseColor("#F97316"));
        } else {
            badgeColor = Color.parseColor("#FEE2E2");
            badgeTextColor = Color.parseColor("#991B1B");
            holder.cardRoot.setStrokeColor(Color.parseColor("#EF4444"));
        }
        holder.tvLevel.setBackgroundTintList(ColorStateList.valueOf(badgeColor));
        holder.tvLevel.setTextColor(badgeTextColor);

        if ("manual".equalsIgnoreCase(event.getActionTaken())) {
            holder.tvAction.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E9D5FF")));
            holder.tvAction.setTextColor(Color.parseColor("#6B21A8"));
        } else {
            holder.tvAction.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#DBEAFE")));
            holder.tvAction.setTextColor(Color.parseColor("#1D4ED8"));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class FireEventViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardRoot;
        TextView tvTime;
        TextView tvLevel;
        TextView tvTemperature;
        TextView tvDirection;
        TextView tvPattern;
        TextView tvAction;
        TextView tvDuration;

        FireEventViewHolder(@NonNull View itemView) {
            super(itemView);
            cardRoot = itemView.findViewById(R.id.cardEventRoot);
            tvTime = itemView.findViewById(R.id.tvEventTime);
            tvLevel = itemView.findViewById(R.id.tvEventLevel);
            tvTemperature = itemView.findViewById(R.id.tvEventTemperature);
            tvDirection = itemView.findViewById(R.id.tvEventDirection);
            tvPattern = itemView.findViewById(R.id.tvEventPattern);
            tvAction = itemView.findViewById(R.id.tvEventAction);
            tvDuration = itemView.findViewById(R.id.tvEventDuration);
        }
    }
}
