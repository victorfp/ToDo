<?php
//nm_tarefa = $_GET['nm_tarefa'];
//$obs = $_GET['obs'];
//$dt_ = $_GET['telefone'];
//$email = $_POST['email'];
//$senha = $_POST['senha'];
//$telefone = $_POST['telefone'];
$conexao = new mysqli('localhost','root','','bdtodolist');
$sql ="select * from tb_tarefa";
$resultado = $conexao->query($sql) or die ("Erro.:" . mysqli_error());
if ($resultado->num_rows > 0){
	while ($row = mysqli_fetch_row($resultado)){
	echo $row[1].";".$row[2].";".$row[3].";".$row[4].";\n" ;
	}
}else{ 
	echo "0";
}

?>