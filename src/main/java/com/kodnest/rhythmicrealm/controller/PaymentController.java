package com.kodnest.rhythmicrealm.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodnest.rhythmicrealm.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RestController
public class PaymentController {
	@Autowired
	UserService userService;

	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(HttpSession session) {
		System.out.println("request received");
		int amount = 1;
		Order order = null;
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_iO7AgTpjgQSek5", "c8Yj6nnhViSSHFUhtgcJw7Nu");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount * 100); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");

			order = razorpay.orders.create(orderRequest);
		} catch (RazorpayException e) {
			e.printStackTrace();
		} finally {
			return order != null ? order.toString() : "{}"; // Ensure a non-null JSON object is returned
		}
	}

	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestBody Map<String, String> requestData) {
		String orderId = requestData.get("orderId");
		String paymentId = requestData.get("paymentId");
		String signature = requestData.get("signature");
		try {
			new RazorpayClient("rzp_test_iO7AgTpjgQSek5", "c8Yj6nnhViSSHFUhtgcJw7Nu");

			String verificationData = orderId + "|" + paymentId;

			return Utils.verifySignature(verificationData, signature, "c8Yj6nnhViSSHFUhtgcJw7Nu");
		} catch (RazorpayException e) {
			e.printStackTrace();
			return false;
		}
	}


	 @PostMapping("/updatePremiumStatus")
	    public ResponseEntity<String> updatePremiumStatus(@RequestBody UpdatePremiumRequest request) {
		 System.out.println(request);
	        try {
	            Long userId = request.getUserId(); // Assuming this is how you receive the userId
	            userService.updatePremiumStatus(userId, true); // Update premium status in your service
	            return ResponseEntity.ok().body("Premium status updated successfully");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Failed to update premium status");
	        }
	    }
	 
	 @GetMapping("/api/user/{userId}/premium")
	 public ResponseEntity<Boolean> getUserPremiumStatus(@PathVariable Long userId) {
	     // Call UserService method to retrieve user's premium status
	     boolean isPremium = userService.isUserPremium(userId);
	     System.out.println(isPremium);
	     // Return premium status in the response
	     return ResponseEntity.ok(isPremium);
	 }

}

class UpdatePremiumRequest {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}



