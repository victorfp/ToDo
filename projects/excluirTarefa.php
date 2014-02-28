<?php
$nm = $_GET['nm'];
$conexao = new mysqli('localhost','root','','bdtodolist');
$sql ="DELETE FROM tb_tarefa WHERE nm_tarefa = '$nm'";
$resultado = $conexao->query($sql) or die ("Erro.:" . mysqli_error());
if ($resultado)
	echo "1";
else 
	echo "0";
?>