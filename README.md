Với dự án môn học này, tôi lựa chọn đề tài điều khiển động cơ bước đa kênh cùng với sự phát triển thêm các chức năng thông minh trên điện thoại.

Dự án có app trên điện thoại android giúp hỗ trợ điều khiển từ xa và lưu trạng thái ở CSDL thời gian thực FIREBASE.
<img width="373" height="804" alt="image" src="https://github.com/user-attachments/assets/a90c4f60-66d6-4c77-b025-84202d383138" />

<img width="312" height="671" alt="image" src="https://github.com/user-attachments/assets/34664371-534c-4476-8c4b-126113a132f5" />

Để điều khiển tốc độ, tôi lựa chọn đọc giá trị adc từ biến trở. Tuy nhiên để giảm chi phí và dùng linh kiện có sẵn thì tôi chọn dùng ESP32 thay thế cho một bộ ADC bình thường. Từ đó sáng tạo thêm chức năng cho dự án, cho phép điều khiển động cơ bằng ứng dụng trên điện thoại mà chương trình chính vẫn là Verilog nằm trên CPLD.

Về phần cứng thì có các nút nhấn điều khiển quay góc (90 độ, 360 độ), quay trái, phải, quay đồng pha và quay ngược pha các động cơ với nhau.

Ở đây là source code cho CPLD, thời gian tới tôi sẽ update thêm các thông tin liên quan đến dự án này
