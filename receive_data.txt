`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:44:27 03/06/2025 
// Design Name: 
// Module Name:    receiva_data 
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
module receive_data(
	input SCK,
	input [7:0] MOSI,
	input CS,
	output reg [7:0] data_out
	//output reg [7:0] data_out_2
    );

always @( posedge SCK) begin

	if (CS) begin
	//	if(MOSI <= 9) begin
	//		data_out_2 <= MOSI;
	//	end
	//	else begin
			data_out <= MOSI;  // Lưu dữ liệu khi LOAD = 1
		end
 //   end
end
endmodule
