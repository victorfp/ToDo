<?php
//$email = $_GET['email'];
$senha = $_GET['senha'];
$telefone = $_GET['telefone'];
//$email = $_POST['email'];
//$senha = $_POST['senha'];
//$telefone = $_POST['telefone'];
$conexao = new mysqli('localhost','root','','bdtodolist');
$sql ="select * from tb_usuario where telefone='$telefone' and senha='$senha'";
$resultado = $conexao->query($sql) or die ("Erro.:" . mysqli_error());
if ($resultado->num_rows > 0)
	echo "1";
else 
	echo "0";

?>