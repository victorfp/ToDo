<?php
//$usuario = $_GET['usuario'];
//$senha = $_GET['senha'];
$usuario = $_POST['usuario'];
$senha = $_POST['senha'];
$conexao = new mysqli('localhost','root','','android');
$sql ="select * from usuario where nome='$usuario' and senha='$senha'";
$resultado = $conexao->query($sql) or die ("Erro.:" . mysqli_error());
if ($resultado->num_rows > 0)
	echo "1";
else 
	echo "0";

?>