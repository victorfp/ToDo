<?php
$nm_tarefa = $_GET['nm_tarefa'];
$obs = $_GET['obs'];
$dt_finalizacao = $_GET['dt_finalizacao'];
$notificar = $_GET['notificar'];
$conexao = new mysqli('localhost','root','','bdtodolist');
$sql ="INSERT INTO tb_tarefa (nm_tarefa, obs, dt_finalizacao, notificar) VALUES ('$nm_tarefa', '$obs', '$dt_finalizacao', '$notificar');";
$resultado = $conexao->query($sql) or die ("Erro.:" . mysqli_error());
if ($resultado)
	echo "1";
else 
	echo "0";

?>
