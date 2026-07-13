import React from 'react';
import { shallow, mount } from 'enzyme';
import CohortDetails from './CohortDetails';
import { CohortsData } from './Cohort';

describe('Cohort Details Component', () => {
    const dummyCohort = CohortsData[0];

    test('should create the component', () => {
        const wrapper = shallow(<CohortDetails cohort={dummyCohort} />);
        expect(wrapper.exists()).toBe(true);
    });

    test('should initialize the props', () => {
        const wrapper = mount(<CohortDetails cohort={dummyCohort} />);
        expect(wrapper.props().cohort).toEqual(dummyCohort);
        wrapper.unmount();
    });

    test('should display cohort code in h3', () => {
        const wrapper = mount(<CohortDetails cohort={dummyCohort} />);
        const h3Text = wrapper.find('h3').text();
        expect(h3Text).toContain(dummyCohort.cohortCode);
        wrapper.unmount();
    });

    test('should always render same html', () => {
        const wrapper = shallow(<CohortDetails cohort={dummyCohort} />);
        expect(wrapper).toMatchSnapshot();
    });
});
