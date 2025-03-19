`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    16:52:59 03/04/2025 
// Design Name: 
// Module Name:    CHIA_1KHZ 
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
module CHIA_10KHZ(
    input clk_ht,
	 input [7:0] din,
    output reg clk_10khz
    );
reg [15:0] counter = 0; // Cần 10-bit cho giá trị đếm 1000
reg [15:0] threshold;

always @(*) begin
	if (din >= 8'd10 && din < 8'd19) 
         threshold = 16'hFFFF;  // Không tạo xung (0Hz)
   else if (din < 8'd31 && din >= 8'd19) 
         threshold = 500;  // 100Hz
   else if (din < 8'd42 && din >= 8'd31) 
         threshold = 250;  // 200Hz
   else if (din < 8'd63 && din >= 8'd42) 
         threshold = 166;  // 300Hz
   else if (din < 8'd84 && din >= 8'd63) 
         threshold = 125;  // 400Hz
   else if (din < 8'd105 && din >= 8'd84) 
         threshold = 100;  // 500Hz
	else if (din < 8'd126 && din >= 8'd105)
			threshold = 83;   // 600Hz
   else if (din < 8'd147 && din >= 8'd126) 
         threshold = 71;   // 700Hz
   else if (din < 8'd168 && din >= 8'd147) 
         threshold = 62;   // 800Hz
   else if (din < 8'd189 && din >= 8'd168) 
         threshold = 55;   // 900Hz
   else if (din < 8'd210 && din >= 8'd189) 
         threshold = 50;   // 1000Hz
   else if (din < 8'd239 && din >= 8'd210) 
         threshold = 45;   // 1100Hz
   else if (din < 8'd256 && din >= 8'd240)
         threshold = 42;   // 1200Hz (din từ 240 đến 255)
	else 
			threshold = 42; 
end
always @ (posedge clk_ht) begin
	if(threshold != 16'hFFFF) begin
      counter <= counter + 1; 
      if (counter == threshold) begin  // Chia tần số xuống 1kHz
          counter <= 0;
          clk_10khz <= ~clk_10khz; // Đảo trạng thái xung
      end
   end else begin
			clk_10khz <= 0;
		end
end
endmodule
