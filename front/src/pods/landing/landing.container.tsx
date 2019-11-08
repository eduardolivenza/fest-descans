import * as React from "react";
import { LandingComponentParent } from "./landing.component";
import { TeamSection } from "./components/teamSection.component";
import { WorkSection } from "./components/workSection.component";
import { withRouter, RouteComponentProps } from "react-router-dom";

interface Props extends RouteComponentProps { }

const LandingContainerInner = (props: Props) => {
  return (
    <LandingComponentParent>
      <TeamSection />
      <WorkSection />
    </LandingComponentParent>
  );
};

export const LandingContainer = withRouter(LandingContainerInner);