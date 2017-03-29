<?php

require_once __DIR__.'/vendor/autoload.php';

use JWTexample\Silex\ApplicationJWT;


$app = new ApplicationJWT();


$app->run();

?>
