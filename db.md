```bash

fire-alarm-system/
│
├── sensors/                        # ESP32 ghi, App đọc (read-only từ app)
│   ├── dht11/                      # Cảm biến nhiệt độ và độ ẩm DHT11
│   │   ├── temperature: 32.5       # °C, float
│   │   ├── humidity: 65.0          # %, float
│   │   └── status: "ok"            # "ok" | "error" (đứt dây / hỏng)
│   ├── mq2/                        # Cảm biến khí gas MQ-2
│   │   ├── value: 210              # ADC raw 0–4095 (12-bit ESP32)
│   │   ├── level: "safe"           # "safe" | "warning" | "danger"
│   │   └── status: "ok"            # "ok" | "error"
│   └── flame/                      # Cảm biến 5 mắt hồng ngoại
│       ├── eye_1: 0                # 0 = không có lửa, 1 = phát hiện lửa
│       ├── eye_2: 0
│       ├── eye_3: 1
│       ├── eye_4: 0
│       ├── eye_5: 0
│       ├── eye_1_raw: 3900         # ADC raw 0–4095, giá trị thô từ mắt hồng ngoại
│       ├── eye_2_raw: 3850
│       ├── eye_3_raw: 800
│       ├── eye_4_raw: 3800
│       ├── eye_5_raw: 3900
│       ├── any_detected: true      # true nếu BẤT KỲ mắt nào = 1  
│       ├── direction: "center"     # "left" | "center-left" | "center" | "center-right" | "right" | "none"
│       └── status: "ok"            # "ok" | "error" (ví dụ: lỗi giao tiếp I2C, hỏng mắt, v.v.)
│
├── actuators/                      # ESP32 ghi, App đọc (read-only từ app)
│   ├── buzzer: false               # true = đang kêu, false = tắt
│   ├── pump: false                 # true = đang bơm, false = tắt
│   ├── auto_pump_active: false     # true = đang tự động bơm do cảnh báo, false = không tự động bơm
│   └── servo/                      # điều khiển servo 2 trục
│       ├── axis_x: 90              # 0–180 độ, 90 = trung tâm
│       └── axis_y: 90              # 0–180 độ, 90 = trung tâm
│
├── system/                         # ESP32 ghi, App đọc (read-only từ app)
│   ├── fire_detected: false        # true = đã phát hiện cháy, false = chưa phát hiện
│   ├── mode: "auto"                # "auto" | "manual" (chế độ hoạt động của servo và bơm)
│   ├── last_seen: 1718000000       # timestamp Unix (giây kể từ 1970-01-01), cập nhật mỗi lần ESP32 gửi dữ liệu
│   └── firmware_version: "1.0.0"   # phiên bản firmware của ESP32
│
├── alert/                          # ESP32 ghi, App đọc (read-only từ app)
│   ├── enabled: true               # true = cảnh báo được bật, false = tắt cảnh báo
│   ├── snoozed: false              # true = đang tạm tắt cảnh báo, false = không tạm tắt
│   ├── snooze_until: 0             # timestamp Unix đến khi nào cảnh báo được tự động bật lại (0 nếu không đang tạm tắt)
│   ├── snooze_duration_min: 10     # thời gian tạm tắt cảnh báo mặc định (phút)
│   ├── last_triggered: 0           # timestamp Unix lần cuối cùng cảnh báo được kích hoạt (0 nếu chưa từng kích hoạt)
│   └── notification_sent: false    # true = đã gửi thông báo (app hoặc email), false = chưa gửi hoặc đã reset sau khi cảnh báo được giải quyết
│
├── thresholds/                     # ESP32 ghi, App đọc (read-only từ app)
│   ├── mq2_safe: 800               # ngưỡng an toàn cho MQ-2 (giá trị ADC dưới ngưỡng này được coi là an toàn)
│   ├── mq2_warning: 1500           # ngưỡng cảnh báo cho MQ-2 (giá trị ADC trên ngưỡng này được coi là cảnh báo)
│   ├── temp_safe: 40.0             # ngưỡng an toàn cho nhiệt độ (độ C)
│   ├── temp_warning: 50.0          # ngưỡng cảnh báo cho nhiệt độ (độ C)
│   └── updated: false              # true = ngưỡng đã được cập nhật, false = chưa được cập nhật
│
├── control/                        # App ghi, ESP32 đọc (chỉ app mới có quyền ghi)
│   ├── pump_on: false              # true = bật bơm, false = tắt bơm
│   ├── buzzer_on: false            # true = bật còi, false = tắt còi
│   └── servo/                      # điều khiển servo 2 trục
│       ├── axis_x: 90              # 0–180 độ, 90 = trung tâm
│       └── axis_y: 90              # 0–180 độ, 90 = trung tâm
│
└── logs/                                           # ESP32 ghi, App đọc (read-only từ app)
    └── {push_key}/                                 # mỗi lần phát hiện cháy sẽ tạo một push_key duy nhất (ví dụ: timestamp hoặc UUID)
        ├── timestamp: 1718000000                   # timestamp Unix khi sự kiện cháy được phát hiện
        ├── time_readable: "2026-04-26 14:32:05"    # thời gian có thể đọc được (định dạng ISO 8601)
        ├── temperature: 55.2                       # °C, float tại thời điểm phát hiện cháy
        ├── humidity: 58.0                          # %, float tại thời điểm phát hiện cháy  
        ├── mq2_value: 1823                         # ADC raw 0–4095 tại thời điểm phát hiện cháy
        ├── mq2_level: "danger"                     # "safe" | "warning" | "danger" tại thời điểm phát hiện cháy
        ├── flame_direction: "center"               # "left" | "center-left" | "center" | "center-right" | "right" | "none" tại thời điểm phát hiện cháy
        ├── flame_pattern: "00100"                  # chuỗi 5 ký tự đại diện cho trạng thái 5 mắt hồng ngoại (1 = phát hiện lửa, 0 = không phát hiện)
        ├── servo_x_at_event: 90                    # góc servo trục X tại thời điểm phát hiện cháy
        ├── servo_y_at_event: 45                    # góc servo trục Y tại thời điểm phát hiện cháy
        ├── action_taken: "auto"                    # "auto" | "manual" (hệ thống tự động phản ứng hay người dùng đã can thiệp)
        ├── pump_activated: true                    # true = bơm đã được kích hoạt để dập lửa, false = bơm không được kích hoạt
        ├── buzzer_activated: true                  # true = còi đã được kích hoạt để cảnh báo, false = còi không được kích hoạt
        ├── alert_was_snoozed: false                # true = cảnh báo đã được tạm tắt (snoozed) tại thời điểm phát hiện cháy, false = cảnh báo không bị tạm tắt
        └── resolved_at: 1718000120                 # timestamp Unix khi sự kiện cháy được giải quyết (ví dụ: khi nhiệt độ giảm xuống dưới ngưỡng an toàn), 0 nếu chưa giải quyết
```