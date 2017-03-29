<?php
namespace Silex\Tests;


use Silex\WebTestCase;
use JWTexample\Silex\ApplicationJWT;


class TellMeASecretControllerTest extends WebTestCase
{
    public function createApplication()
    {
        $app = new ApplicationJWT();

        $app['debug'] = true;
        unset($app['exception_handler']);
        return $app;

    }

    public function testTellMeASecretWithoutToken()
    {

          $client = $this->createClient();

          $crawler = $client->request(
                                    'GET',
                                    '/secret/tellmeasecret',
                                    array(),
                                    array(),
                                    array('CONTENT_TYPE'  => 'application/json')
                                  );

          $this->assertEquals(
              401,
              $client->getResponse()->getStatusCode()
          );

    }

    public function testTellMeASecretWithToken()
    {

          $client = $this->createClient();

          $token = $this->app['security.jwt.encoder']->encode(['name' => 'admin']);

          $crawler = $client->request(
                                    'GET',
                                    '/secret/tellmeasecret',
                                    array(),
                                    array(),
                                    array('CONTENT_TYPE'  => 'application/json',
                                          'HTTP_x-auth-token' => $token)
                                  );

          $this->assertTrue($client->getResponse()->isOk());

          //decode json responce
          $content=json_decode($client->getResponse()->getContent(), true);

          $this->assertContains('You are my father!!!',$content['responce']);
    }

}

?>
