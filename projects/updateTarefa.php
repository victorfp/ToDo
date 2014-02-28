<?php
$nm = $_GET['nm'];
$obs = $_GET['obs'];
$data = $_GET['data'];
$notificar = $_GET['not'];

$conexao = new mysqli('localhost','root','','bdtodolist');
$sql ="UPDATE tb_tarefa SET obs='$obs',dt_finalizacao='$data',notificar='$notificar' WHERE nm_tarefa='$nm'";
$resultado = $conexao->query($sql) or die ("Erro.:" . mysqli_error());
if ($resultado)
	echo "1";
else 
	echo "0";

?>