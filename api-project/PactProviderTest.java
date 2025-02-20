package LiveProject;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.HttpsTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@Provider("UserProvider")
@PactFolder("target/pacts")
public class PactProviderTest {
     @BeforeEach
     @Test
        public void SetUp(PactVerificationContext context){
         HttpTestTarget target=new HttpTestTarget("localhost",8585);
         context.setTarget(target);
      }

      @TestTemplate
      @ExtendWith(PactVerificationInvocationContextProvider.class)
      public void ProviderTest(PactVerificationContext context){
          context.verifyInteraction();
     }
     @State("A request to create a user")
        public void providerTest(){}
}


