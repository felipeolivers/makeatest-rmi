MakeATest-RMI Plugin
====================
**Visão Geral do Plugin**

Este plugin tem como objetivo permitir a criação de testes de unidade para projetos de sistemas distribuídos em Java utilizando RMI de maneira que a configuração de inicialização, simulação e preparação do ambiente de testes com os objetos remotos possam ser realizadas através de suas anotações.

Utilização
----------

*Exemplo*:


### Classe de Teste de Unidade (*JUnit*)

	@RunWith(MakeATestRunner.class)
	@StartRMIServer(codebasePath="C:\\Java\\Workspace\\ChatMockFrameworkRMITest\\chat-common\\",
					securityPolicyPath="C:\\Java\\Workspace\\ChatMockFrameworkRMITest\\chat-test\\")
	@StopRMIServer()				
	public class ChatRMITestServerMock {
		
		public Mockery context;	
		public IMessage server;
		
		// IMessage
		public static IMessage chatServer;
			
	    @Test
	    @PutMockRMI(fixtureName="server", serverName="itachat", serverPort=1099)
	    @RemoveMockRMI(serverName="itachat")
		public void testServerJoinMessage() throws RemoteException, MalformedURLException, NotBoundException {
			// Lookup Object
			// -->	   
	    	System.out.println("testServerJoinMessage");
			System.setSecurityManager (new RMISecurityManager());
			Remote remoteObject = Naming.lookup("itachat");
			chatServer = (IMessage)remoteObject ;
	    	
	    	// INotify
	    	final INotify stubNotify = null;
	    	
	    	// Expectation
	        context.checking(new Expectations() {{
	        	one(server).join(with(any(INotify.class)), with(any(String.class)));
	        	will(returnValue(true));
	        }});
	               
	        // Assert
	        assertTrue(chatServer.join(stubNotify, "Felipe"));
	        context.assertIsSatisfied();
		}
	    
	    @Test
	    @PutMockRMI(fixtureName="server", serverName="itachat-2", serverPort=1099)
	    @RemoveMockRMI(serverName="itachat-2")
		public void testServerJoinMessageOtherServer() throws RemoteException, MalformedURLException, NotBoundException {
			// Lookup Object
			// -->
	    	System.out.println("testServerJoinMessageOtherServer");
			System.setSecurityManager (new RMISecurityManager());
			Remote remoteObject = Naming.lookup("itachat-2");
			chatServer = (IMessage)remoteObject ;
	    	
	    	// INotify
	    	final INotify stubNotify = null;
	    	
	    	// Expectation
	        context.checking(new Expectations() {{
	        	one(server).join(with(any(INotify.class)), with(any(String.class)));
	        	will(returnValue(true));
	        }});
	               
	        // Assert
	        assertTrue(chatServer.join(stubNotify, "Felipe"));
	        context.assertIsSatisfied();
		}
	    
	}


Anotações
---------

As anotações do plugin para testes de unidade com sistemas distribuídos em Java com RMI.


*   **@StartRMIServer**
*(Inicializa o serviço RMI com as configurações iniciais)*
*   **@StopRMIServer**
*(Para o serviço RMI)*
*   **@PutMockRMI**
*(Inclui um objeto remoto (Mock Object) no serviço RMI)*
*   **@RemoveMockRMI**
*(Remove do serviço RMI o objeto remoto (Mock Object))*
*   **@AssertObjectOnServer**
*(Verifica se o objeto remoto está no RMI)*
*   **@AssertObjectNotOnServer**
*(Verifica se o objeto remoto não está no RMI)*


Dependências
------------

*   [Eclipse](http://www.eclipse.org/)
*   [JUnit 4](https://github.com/KentBeck/junit)
*   [JMock](http://www.jmock.org/)
*   [MakeATest-Core](https://github.com/marcusfloriano/makeatest-core)


Licença
-------

Copyright (c) 20011 Felipe Olivers. This is a free software is licensed under the MIT License.