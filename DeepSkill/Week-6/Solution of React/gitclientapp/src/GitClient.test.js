import React from 'react';
import { shallow } from 'enzyme';
import axios from 'axios';
import GitClient from './GitClient';

jest.mock('axios');

describe('Git Client Tests', () => {
    test('should return repository names for techiesyed', () => {
        const mockData = {
            data: [
                { name: 'repo-one' },
                { name: 'repo-two' },
                { name: 'repo-three' }
            ]
        };

        axios.get.mockResolvedValue(mockData);

        const wrapper = shallow(<GitClient />);
        
        return wrapper.instance().getRepositories().then(repoNames => {
            expect(repoNames).toEqual(['repo-one', 'repo-two', 'repo-three']);
            expect(wrapper.state('repos')).toEqual(['repo-one', 'repo-two', 'repo-three']);
            expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/techiesyed/repos');
        });
    });
});
