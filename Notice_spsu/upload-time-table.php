<?php
	require 'connection.php';
	
	if(isset($_POST['branch'])){
		$file_name = "";
		$msg = "";
		$semester = strtoupper(mysqli_real_escape_string($con, validate($_POST['semester'])));
		$branch = strtoupper(mysqli_real_escape_string($con, validate($_POST['branch'])));
		
		$target_dir = "timetable/";
		if (!file_exists($target_dir)) {
			mkdir($target_dir, 0777, true);
		}
		
		$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
		$imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
		$file_name = $semester.' '.$branch.'.'.$imageFileType;
		
		if($_FILES["fileToUpload"]["size"] < 2500000){

			if($imageFileType == "png" || $imageFileType == "jpg" || $imageFileType == "jpeg") {

				if(move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_dir.$file_name)) {
					if(mysqli_query($con, "delete from tb_timetable where semester = '$semester' and branch = '$branch'")){
						
					}
					$run = mysqli_query($con, "insert into tb_timetable values('$semester','$branch','$file_name')");
					if(mysqli_affected_rows($con)){
						$msg = "Upload Successfully !";
					}
				}else {
					$msg = "Upload failed !";
				}
			}else{
				$msg = "Upload file type error !";
			}
		}else{
			$msg = "Upload too large !";
		}
		header("location:upload-from-here.php?msg=".$msg);
	}
	
	function validate($data){
		$data = trim($data);
		$data = stripslashes($data);
		$data = htmlspecialchars($data);
		return $data;
	}
?>