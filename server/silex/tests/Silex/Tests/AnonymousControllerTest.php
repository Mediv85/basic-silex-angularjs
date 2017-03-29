<?php
namespace Silex\Tests;


use Silex\WebTestCase;
use JWTexample\Silex\ApplicationJWT;


class AnonymousControllerTest extends WebTestCase
{
    public function createApplication()
    {
        $app = new ApplicationJWT();

        $app['debug'] = true;
        unset($app['exception_handler']);
        return $app;

    }

    public function testGreetings()
    {

          $client = $this->createClient();
          $crawler = $client->request(
                                    'GET',
                                    '/anonymous/greetings',
                                    array(),
                                    array(),
                                    array('CONTENT_TYPE'  => 'application/json')
                                  );

          $this->assertTrue($client->getResponse()->isOk());

          //decode json responce
          $content=json_decode($client->getResponse()->getContent(), true);

          $this->assertContains('Hey!!! How is going???',$content['responce']);


    }
}

?>
