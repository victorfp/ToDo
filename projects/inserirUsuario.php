<?php
$email = $_GET['email'];
$senha = $_GET['senha'];
$telefone = $_GET['telefone'];
//$email = $_POST['email'];
//$senha = $_POST['senha'];
//$telefone = $_POST['telefone'];
$conexao = new mysqli('localhost','root','','bdtodolist');
$sql ="INSERT INTO tb_usuario (email, telefone, senha) VALUES ('$email', '$telefone', '$senha')";
$resultado = $conexao->query($sql) or die ("Erro.:" . mysqli_error());
if ($resultado)
	echo "1";
else 
	echo "0";

?>
