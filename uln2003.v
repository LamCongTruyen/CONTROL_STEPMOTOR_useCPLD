`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    16:56:23 03/04/2025 
// Design Name: 
// Module Name:    uln2003 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
module uln2003(
    input clk_10khz,
    input dir_left , //quay trai
	 input dir_right,	//quay phai
	 input mode1,		//quay 90 do
	 input mode2,		// quay 360 do
	 input [7:0] esp_command, //du lieu dieu khien bang dien thoai gui tu esp
	 input [1:0] motor_select,  // Chọn động cơ hoạt động (00: Tắt cả, 01: Chỉ động cơ 1, 10: Chỉ động cơ 2, 11: Cả hai)
    input sync_mode,           // 0: Đồng bộ, 1: Ngược pha
    output reg [3:0] step_out, //ngo ra dong co thu 1
	 output reg [3:0] step_out_2 //ngo ra dong co thu 2
    );
///////////////////////////////////////////////////////////////////////

reg [2:0] step_index = 0;
reg [2:0] step_index_2 = 0;
reg [12:0] step_count = 0; // Đếm số bước (2048 bước cần thiết)
reg [12:0] step_count_2 = 0;
reg [31:0] timer; 
reg [31:0] timer_2;
//////////////////////////////////////////////////////////////////////////////////

always @(posedge clk_10khz) begin
if(esp_command == 8'd0) begin
	step_index <= 3'd8;
	step_index_2 <= 3'd8;
end
else if(esp_command == 8'd1) begin
	step_index <= step_index + 1;
end
else if(esp_command == 8'd2) begin
	step_index <= step_index - 1;
end
else if(esp_command == 8'd3) begin
	step_index_2 <= step_index_2 + 1;
end
else if(esp_command == 8'd4) begin
	step_index_2 <= step_index_2 - 1;
end
else if(esp_command == 8'd5) begin
	step_index <= step_index + 1;
   step_index_2 <= step_index_2 + 1;
end
else if(esp_command == 8'd6) begin
	step_index <= step_index - 1;
   step_index_2 <= step_index_2 - 1;
end
else begin
//////////////////////////////////////////////////////////////////////////////////
	case (motor_select)
	
		2'b00: begin		
			step_index <= 3'd8;  // Cả hai động cơ dừng
			step_index_2 <= 3'd8;
		end
		
		2'b01: begin		//dong co thu nhat hoat dong
			if(mode1 == 0 && mode2 == 0) begin  //neu ca hai cung kich hoat thi dung lai dong co
				if (dir_left == 1 && dir_right == 1) begin
					step_index <= 3'd8;
					end				
				else if(dir_right) begin// Quay ngược chiều kim đồng hồ (CCW)
					step_index <= step_index - 1;
					end				
				else if(dir_left) begin 
					step_index <= step_index + 1;
					end					
				else begin// Quay ngược chiều kim đồng hồ (CCW)
					step_index <= 3'd8;
					end	
				timer <= 0;
			end
//////////////////////////////////////////////////////////////////////////////////
			else if(mode1 == 1) begin // nut nhan lua chon che do theo goc 90 do
				if(dir_right) begin
					if (timer < 1024) begin
						step_index <= step_index - 1;
						if(step_index == 3'd0) begin
								step_index <= 3'd7;
								end
						timer <= timer + 1;
					end
					else begin
						step_index <= 3'd8; // Dừng động cơ khi đạt 90 độ
					end
				end
				else if(dir_left) begin
					if (timer < 1024) begin
						step_index <= step_index + 1;
						if(step_index == 3'd7) begin
							step_index <= 3'd0;
						end
						timer <= timer + 1;
						end
					else begin
						step_index <= 3'd8; // Dừng động cơ khi đạt 90 độ
						end
				end
				else begin
					timer <= 0;
				end
			end
//////////////////////////////////////////////////////////////////////////////////
			else if (mode2 == 1) begin //nut nhan lua chon che do goc quay 360 do
				if(dir_right) begin
					if (timer < 4096) begin
						step_index <= step_index - 1;
						if(step_index == 3'd0) begin
							step_index <= 3'd7;	
						end
						timer <= timer + 1;
					end
					else begin
						step_index <= 3'd8; // Dừng động cơ khi đạt 360 độ
					end
				end 
				else if(dir_left) begin
					if (timer < 4096) begin
						step_index <= step_index + 1;
						if(step_index == 3'd7) begin
							step_index <= 3'd0;	
						end
						timer <= timer + 1;
					end
					else begin
						step_index <= 3'd8; // Dừng động cơ khi đạt 360 độ
					end
				end 
				else begin
					timer <= 0;
				end
			end
		end
		
		2'b10: begin   //dong co thu hai hoat dong
		////
			if(mode1 == 0 && mode2 == 0) begin  //neu ca hai cung kich hoat thi dung lai dong co
				if (dir_left == 1 && dir_right == 1) begin
					step_index_2 <= 3'd8;
					end				
				else if(dir_right) begin// Quay ngược chiều kim đồng hồ (CCW)
					step_index_2 <= step_index_2 - 1;
					end				
				else if(dir_left) begin 
					step_index_2 <= step_index_2 + 1;
					end					
				else begin// Quay ngược chiều kim đồng hồ (CCW)
					step_index_2 <= 3'd8;
					end	
				timer_2 <= 0;
			end
//////////////////////////////////////////////////////////////////////////////////
			else if(mode1 == 1) begin // nut nhan lua chon che do theo goc 90 do
				if(dir_right) begin
					if (timer_2 < 1024) begin
						step_index_2 <= step_index_2 - 1;
						if(step_index_2 == 3'd0) begin
								step_index_2 <= 3'd7;
								end
						timer_2 <= timer_2 + 1;
					end
					else begin
						step_index_2 <= 3'd8; // Dừng động cơ khi đạt 90 độ
					end
				end
				else if(dir_left) begin
					if (timer_2 < 1024) begin
						step_index_2 <= step_index_2 + 1;
						if(step_index_2 == 3'd7) begin
							step_index_2 <= 3'd0;
						end
						timer_2 <= timer_2 + 1;
						end
					else begin
						step_index_2 <= 3'd8; // Dừng động cơ khi đạt 90 độ
						end
				end
				else begin
					timer_2 <= 0;
				end
			end
//////////////////////////////////////////////////////////////////////////////////
			else if (mode2 == 1) begin //nut nhan lua chon che do goc quay 360 do
				if(dir_right) begin
					if (timer_2 < 4096) begin
						step_index_2 <= step_index_2 - 1;
						if(step_index_2 == 3'd0) begin
							step_index_2 <= 3'd7;	
						end
						timer_2 <= timer_2 + 1;
					end
					else begin
						step_index_2 <= 3'd8; // Dừng động cơ khi đạt 360 độ
					end
				end 
				else if(dir_left) begin
					if (timer_2 < 4096) begin
						step_index_2 <= step_index_2 + 1;
						if(step_index_2 == 3'd7) begin
							step_index_2 <= 3'd0;	
						end
						timer_2 <= timer_2 + 1;
					end
					else begin
						step_index_2 <= 3'd8; // Dừng động cơ khi đạt 360 độ
					end
				end 
				else begin
					timer_2 <= 0;
				end
			end
		end
		
		2'b11: begin
		////
			if (dir_left && dir_right) begin
             step_index <= step_index;
             step_index_2 <= step_index_2;
         end
			else begin
             if (dir_right) begin
						step_index <= step_index - 1;
						step_index_2 <= (sync_mode) ? step_index_2 + 1 : step_index - 1; // Ngược pha hoặc đồng bộ
				 end 
				 else if (dir_left) begin
						step_index <= step_index + 1;
						step_index_2 <= (sync_mode) ? step_index_2 - 1: step_index + 1;
				 end
			end
		end
	endcase
end
end //end always

//////////////////////////////////////////////////////////////////////////////////

always @(*) begin

	case (step_index)
        3'd0: step_out <= 4'b1000;  // Bước 1
        3'd1: step_out <= 4'b1100;  // Bước 2
        3'd2: step_out <= 4'b0100;  // Bước 3
        3'd3: step_out <= 4'b0110;  // Bước 4
        3'd4: step_out <= 4'b0010;  // Bước 5
        3'd5: step_out <= 4'b0011;  // Bước 6
        3'd6: step_out <= 4'b0001;  // Bước 7
        3'd7: step_out <= 4'b1001;  // Bước 8
        default: step_out <= 4'b0000; // Mặc định tắt động cơ
    endcase

    case (step_index_2)
        3'd0: step_out_2 = 4'b1000;
        3'd1: step_out_2 = 4'b1100;
        3'd2: step_out_2 = 4'b0100;
        3'd3: step_out_2 = 4'b0110;
        3'd4: step_out_2 = 4'b0010;
        3'd5: step_out_2 = 4'b0011;
        3'd6: step_out_2 = 4'b0001;
        3'd7: step_out_2 = 4'b1001;
        default: step_out_2 = 4'b0000;
    endcase

end //end always
//////////////////////////////////////////////////////////////////////////////////

endmodule
