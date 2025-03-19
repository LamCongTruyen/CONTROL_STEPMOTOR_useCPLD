`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    16:51:52 03/04/2025 
// Design Name: 
// Module Name:    top_module 
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
module top_module(
    input CLK,
	// input SCK,
	 input [7:0] MOSI,
	 input CS,
	 input dir_left,
	 input dir_right,
	 input [1:0] motor_select,  // Chọn động cơ hoạt động (00: Tắt cả, 01: Chỉ động cơ 1, 10: Chỉ động cơ 2, 11: Cả hai)
    input sync_mode,
	 input mode1,//quay 90do
	 input mode2, //quay 360do
    output [3:0] LED,
	 output [3:0] LED_2
    );

wire [7:0] w; //khai bao tin hieu ket noi ben trong
wire w2;
wire [7:0] w3;

// Kết nối theo thứ tự
 
CHIA_10KHZ   U1 (CLK, w, w2); 
uln2003 U2 (w2, dir_left, dir_right, mode1, mode2, w, motor_select, sync_mode, LED, LED_2);
receive_data U3 (CLK, MOSI, CS, w);	
endmodule
