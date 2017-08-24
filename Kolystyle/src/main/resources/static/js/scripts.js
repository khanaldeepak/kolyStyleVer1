/**
 * 
 */
function checkBillingAddress(){
	if($("#theSameAsShippingAddress").is(":checked")){
		$(".billingAddress").prop("disabled",true);
	}else{
		$(".billingAddress").prop("disabled",false);
	}
}

function checkPasswordMatch(){
	var password = $("#txtNewPassword").val();
	var confirmPassword = $("#txtConfirmPassword").val();
	
	if(password == "" && confirmPassword == ""){
		$("#checkPasswordMatch").html("");
		$("#updateUserInfoButton").prop('disabled',false);
	}else{
		if(password.length < 6){
			$("#checkPasswordMatch").html("Passwords must be atleast 6 characters!");
			$("#updateUserInfoButton").prop('disabled',true);
		}else if(password != confirmPassword){
			$("#checkPasswordMatch").html("Passwords do not match!");
			$("#updateUserInfoButton").prop('disabled',true);
		}else{
			$("#checkPasswordMatch").html("Passwords match");
			$("#updateUserInfoButton").prop('disabled',false);
		}
	}
}

$(document).ready(function(){
	$(".cartItemQty").on('change',function(){
		var id=this.id;
		if(this.value!=''){
		$('#update-item-'+id).css('display','inline-block');
		}else{
		$('#update-item-'+id).css('display','none');
		}
	});
	
	$("#theSameAsShippingAddress").on('click',checkBillingAddress);
	$("#txtConfirmPassword").keyup(checkPasswordMatch);
	$("#txtNewPassword").keyup(checkPasswordMatch);
});